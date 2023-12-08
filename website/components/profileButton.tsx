import { Button } from "@nextui-org/button";
import Link from "next/link";

export const ProfileButton = () => {
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
            Profile
          </Button>
        </Link>
      </div>
      <div className="md:hidden visible">
        <Link href="/profile">
          <div className="text-black dark:text-white font-bold text-xl">
            Profile
          </div>
        </Link>
      </div>
    </>
  );
};
