"use client";
import { ProfileImage } from "@/components/icons";
import { Card } from "@nextui-org/react";
import { useEffect, useState } from "react";

export interface ProfileData {
  id: number;
  username: string;
  email: string;
  admin: boolean;
  points: number;
}

export default function Profile() {
  const [token, setToken] = useState<string>("");
  const [profileData, setProfileData] = useState<ProfileData>();

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
    <div className="flex flex-row justify-center space-x-10 items-center w-full">
      <div className="space-y-5 flex flex-col justify-center items-center">
        <Card className="p-10 space-y-5">
          <div>
            <img src="/assets/images/profile.png" width={200} height={200} />
          </div>
          <div className="text-3xl font-bold w-full justify-center flex">
            Profile
          </div>
          <div className="w-full space-y-5">
            <div>
              <div className="font-bold">Username</div>
              <div>{profileData?.username}</div>
            </div>
            <div>
              <div className="font-bold">Email</div>
              <div>{profileData?.email}</div>
            </div>
            <div>
              <div className="font-bold">Score</div>
              <div>{profileData?.points}</div>
            </div>
          </div>
        </Card>
      </div>
      <ProfileImage />
    </div>
  );
}
