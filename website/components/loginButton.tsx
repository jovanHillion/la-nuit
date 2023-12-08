import { Button } from "@nextui-org/button";
import Link from "next/link";

export const LoginButton = () => {
  return (
    <>
      <div className="hidden md:block">
        <Link href="/login">
          <Button
            size="lg"
            className="bg-transparent text-primary-200 font-bold text-xl"
          >
            Login
          </Button>
        </Link>
      </div>
      <div className="md:hidden visible">
        <Link href="/login">
          <div className="text-black dark:text-white font-bold text-xl">
            Login
          </div>
        </Link>
      </div>
    </>
  );
};
