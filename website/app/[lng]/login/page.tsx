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

interface LoginJson {
  username: string;
  password: string;
}

export default function Login() {
  const [loginJson, setLoginJson] = useState<LoginJson>({
    username: "",
    password: "",
  });
  const [isVisible, setIsVisible] = useState(false);
  const toggleVisibility = () => setIsVisible(!isVisible);
  const [error, setError] = useState<string>("");

  const register = async () => {
    if (loginJson.username === "" || loginJson.password === "") {
      setError("Please fill all the fields");
      return;
    }
    setError("");
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}/user/login`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginJson),
      }
    );
    const data = await response.json();
    if (data.status === 200) {
      localStorage.setItem("token", data.data);
      window.location.href = "/";
      setLoginJson({
        username: "",
        password: "",
      });
      return;
    } else if (data.status === 401) {
      setError("Bad password or username !");
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
              value={loginJson.username}
              onChange={(e) => {
                setLoginJson({
                  ...loginJson,
                  username: e.target.value,
                });
              }}
              startContent={<UserIcon width={20} height={20} />}
            />
            <Input
              variant="underlined"
              className="border-primary-300 border-b-2"
              placeholder="  Password"
              value={loginJson.password}
              onChange={(e) => {
                setLoginJson({
                  ...loginJson,
                  password: e.target.value,
                });
              }}
              startContent={<PasswordIcon width={20} height={20} />}
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
            />
            <Button
              className="w-1/2 bg-primary-200 text-white font-bold text-3xl py-8"
              radius="full"
              onClick={register}
            >
              Login
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
