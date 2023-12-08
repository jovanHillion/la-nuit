"use client";
import {
  Navbar as NextUINavbar,
  NavbarContent,
  NavbarMenu,
  NavbarMenuToggle,
  NavbarBrand,
  NavbarItem,
  NavbarMenuItem,
} from "@nextui-org/navbar";
import NextLink from "next/link";
import { ThemeSwitch } from "@/components/theme-switch";
import { Logo } from "@/components/icons";
import { LoginButton } from "./loginButton";
import { RegisterButton } from "./registerButton";
import { useEffect, useState } from "react";
import { ProfileButton } from "./profileButton";

export const Navbar = () => {
  const [token, setToken] = useState<string>("");

  useEffect(() => {
    setToken(localStorage.getItem("token")?.toString() || "");
  }, []);

  return (
    <NextUINavbar maxWidth="xl" position="sticky">
      <NavbarContent className="basis-1/5 sm:basis-full" justify="start">
        <NavbarBrand as="li" className="gap-3 max-w-fit">
          <NextLink className="flex justify-start items-center gap-1" href="/">
            <Logo width={300} height={300} />
          </NextLink>
        </NavbarBrand>
      </NavbarContent>

      <NavbarContent
        className="hidden sm:flex basis-1/3 sm:basis-full"
        justify="end"
      >
        <NavbarItem className="hidden sm:flex gap-2">
          <ThemeSwitch />
        </NavbarItem>
        {token ? (
          <NavbarItem className="hidden sm:flex gap-2">
            <ProfileButton />
          </NavbarItem>
        ) : (
          <>
            <NavbarItem className="hidden sm:flex gap-2">
              <LoginButton />
            </NavbarItem>
            <NavbarItem className="hidden sm:flex gap-2">
              <RegisterButton />
            </NavbarItem>
          </>
        )}
      </NavbarContent>

      <NavbarContent className="sm:hidden basis-1 pl-4" justify="end">
        <ThemeSwitch />
        <NavbarMenuToggle />
      </NavbarContent>

      <NavbarMenu>
        <div className="mx-4 mt-2 items-end flex flex-col gap-2">
          <NavbarMenuItem>
            <LoginButton />
            <RegisterButton />
          </NavbarMenuItem>
        </div>
      </NavbarMenu>
    </NextUINavbar>
  );
};
