"use client";

import { useEffect } from "react";

export default function Login() {
  useEffect(() => {
    if (localStorage.getItem("token")) {
      window.location.href = "/";
    }
  }, []);
  return (
    <div className="flex justify-center items-center h-full">Login Page</div>
  );
}
