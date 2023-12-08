"use client";
import { useEffect, useState } from "react";

export default function Profile() {
  const [token, setToken] = useState<string>("");

  useEffect(() => {
    if (localStorage.getItem("token")) {
      setToken(localStorage.getItem("token")?.toString() || "");
    } else {
      window.location.href = "/";
    }
  }, []);

  return (
    <div className="flex justify-center items-center h-full">Profile Page</div>
  );
}
