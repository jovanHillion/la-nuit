"use client";
import { ProfileData } from "@/app/[lng]/profile/page";
import { Button } from "@nextui-org/button";
import Link from "next/link";
import { useEffect, useState } from "react";

export const ProfileButton = () => {
  const [profileData, setProfileData] = useState<ProfileData>();
  const [token, setToken] = useState<string>("");

  useEffect(() => {
    (async () => {
      try {
        if (localStorage.getItem("token")) {
          setToken(localStorage.getItem("token")?.toString() || "");
        } else {
          window.location.href = "/";
        }
        const response = await fetch(
          `${process.env.NEXT_PUBLIC_API_URL}/user/me`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
          }
        );
        const data = await response.json();
        if (data.status === 200) {
          setProfileData(data.data);
        }
      } catch (e) {
        console.error(e);
      }
    })();
  }, []);

  return (
    <>
      <div className="hidden md:block">
        <Link href="/profile">
          <Button
            size="lg"
            className="bg-primary-200 text-white font-bold text-xl"
            radius="full"
            startContent={
              <img src="/assets/images/profile.png" width={40} height={40} />
            }
          >
            {profileData?.username}
          </Button>
        </Link>
      </div>
      <div className="md:hidden visible">
        <Link href="/profile">
          <div className="text-black dark:text-white font-bold text-xl">
            {profileData?.username}
          </div>
        </Link>
      </div>
    </>
  );
};
