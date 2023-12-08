"use client";
import {
  EmailIcon,
  LoginIcon,
  PasswordIcon,
  UserIcon,
} from "@/components/icons";
import { Button, Image, Input } from "@nextui-org/react";
import { useEffect, useState } from "react";
import { EyeFilledIcon, EyeSlashFilledIcon } from "@/components/icons";

interface RegisterJson {
  email: string;
  username: string;
  password: string;
  passwordVerif: string;
}

interface RegisterJsonForm {
  email: string;
  username: string;
  password: string;
}

export default function Register() {
  const [registerJson, setRegisterJson] = useState<RegisterJson>({
    email: "",
    username: "",
    password: "",
    passwordVerif: "",
  });
  const [registerJsonForm, setRegisterJsonForm] = useState<RegisterJsonForm>({
    email: "",
    username: "",
    password: "",
  });
  const [error, setError] = useState<string>("");
  const [isVisible, setIsVisible] = useState(false);
  const toggleVisibility = () => setIsVisible(!isVisible);

  const [isVisibleVerif, setIsVisibleVerif] = useState(false);
  const toggleVisibilityVerif = () => setIsVisibleVerif(!isVisibleVerif);

  const register = async () => {
    if (
      registerJson.email === "" ||
      registerJson.username === "" ||
      registerJson.password === "" ||
      registerJson.passwordVerif === ""
    ) {
      setError("Please fill all the fields");
      return;
    }
    if (registerJson.password !== registerJson.passwordVerif) {
      setError("Password not match");
      return;
    }
    if (registerJson.password.length < 8) {
      setError("Password must be at least 8 characters");
      return;
    }
    if (
      RegExp(
        "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$"
      ).test(registerJson.password) === false
    ) {
      setError(
        "Password must contain at least one letter, one number and one special character"
      );
      return;
    }
    if (registerJson.username.length < 3) {
      setError("Username must be at least 3 characters");
      return;
    }
    if (!registerJson.email.includes("@")) {
      setError("Email not valid");
      return;
    }
    setError("");
    setRegisterJsonForm({
      email: registerJson.email,
      username: registerJson.username,
      password: registerJson.password,
    });
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}/user/register`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(registerJsonForm),
      }
    );
    const data = await response.json();
    if (data.status === 200) {
      localStorage.setItem("token", data.data);
      window.location.href = "/";
      setRegisterJson({
        email: "",
        username: "",
        password: "",
        passwordVerif: "",
      });
      setRegisterJsonForm({
        email: "",
        username: "",
        password: "",
      });
      return;
    } else if (data.status === 400) {
      setError(data.message);
      return;
    } else if (data.status === 409) {
      setError(data.message);
      return;
    }
  };

  useEffect(() => {
    if (localStorage.getItem("token")) {
      window.location.href = "/";
    }
  }, []);

  return (
    <div className="w-full flex justify-center items-center">
      <div className="flex justify-center items-center h-full w-[90%]">
        <div className="w-1/2">
          <LoginIcon width={700} height={700} />
        </div>
        <div className="w-1/2 flex justify-center flex-col items-center">
          <div className="w-[80%] flex flex-col space-y-8 justify-center items-center">
            <div>
              <Image
                src="/assets/images/profile.png"
                width={150}
                height={150}
              />
            </div>
            <div className="text-5xl font-bold">Welcome</div>
            <div className="text-red-500 absolute -translate-y-28">{error}</div>
            <Input
              variant="underlined"
              placeholder="  Username"
              className="border-primary-300 border-b-2"
              value={registerJson.username}
              onChange={(e) => {
                setRegisterJson({
                  ...registerJson,
                  username: e.target.value,
                });
              }}
              startContent={<UserIcon width={20} height={20} />}
            />
            <Input
              variant="underlined"
              className="border-primary-300 border-b-2"
              placeholder="  Email"
              value={registerJson.email}
              onChange={(e) => {
                setRegisterJson({
                  ...registerJson,
                  email: e.target.value,
                });
              }}
              startContent={<EmailIcon width={20} height={20} />}
            />
            <Input
              variant="underlined"
              className="border-primary-300 border-b-2"
              placeholder="  Password"
              value={registerJson.password}
              onChange={(e) => {
                setRegisterJson({
                  ...registerJson,
                  password: e.target.value,
                });
              }}
              endContent={
                <button
                  className="focus:outline-none"
                  type="button"
                  onClick={toggleVisibility}
                >
                  {isVisible ? (
                    <EyeSlashFilledIcon className="text-2xl text-default-400 pointer-events-none" />
                  ) : (
                    <EyeFilledIcon className="text-2xl text-default-400 pointer-events-none" />
                  )}
                </button>
              }
              type={isVisible ? "text" : "password"}
              startContent={<PasswordIcon width={20} height={20} />}
            />
            <Input
              className="border-primary-300 border-b-2"
              variant="underlined"
              placeholder="  Confirm password"
              value={registerJson.passwordVerif}
              onChange={(e) => {
                setRegisterJson({
                  ...registerJson,
                  passwordVerif: e.target.value,
                });
              }}
              endContent={
                <button
                  className="focus:outline-none"
                  type="button"
                  onClick={toggleVisibilityVerif}
                >
                  {isVisibleVerif ? (
                    <EyeSlashFilledIcon className="text-2xl text-default-400 pointer-events-none" />
                  ) : (
                    <EyeFilledIcon className="text-2xl text-default-400 pointer-events-none" />
                  )}
                </button>
              }
              type={isVisibleVerif ? "text" : "password"}
              startContent={<PasswordIcon width={20} height={20} />}
            />
            <Button
              className="w-1/2 bg-primary-200 text-white font-bold text-3xl py-8"
              radius="full"
              onClick={register}
            >
              Register
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
