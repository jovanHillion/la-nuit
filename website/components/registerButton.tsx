import { Button } from "@nextui-org/button";
import Link from "next/link";

export const RegisterButton = () => {
  return (
    // <div>
    //   <Link href="/register">
    //     <Button
    //       size="lg"
    //       className="bg-primary-200 text-white font-bold text-xl"
    //     >
    //       Sign up
    //     </Button>
    //   </Link>
    // </div>

    <>
      <div className="hidden md:block">
        <Link href="/register">
          <Button
            size="lg"
            className="bg-primary-200 text-white font-bold text-xl"
          >
            Sign up
          </Button>
        </Link>
      </div>
      <div className="md:hidden visible">
        <Link href="/register">
          <div className="text-black dark:text-white font-bold text-xl">
            Sign up
          </div>
        </Link>
      </div>
    </>
  );
};
