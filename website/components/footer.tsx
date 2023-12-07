import Link from "next/link";
import { HeartFilledIcon } from "./icons";

export const Footer = () => {
  return (
    <footer className="w-full flex items-center justify-center pt-10 pb-5">
      <Link className="flex items-center gap-1 text-current" href="developper">
        <span className="text-black dark:text-white">Made with</span>
        <HeartFilledIcon size={20} className="text-red-500" />
        <p className="text-black dark:text-white">by</p>
        <p className="text-primary-300 dark:text-primary-200">
          Jovan<span className="text-black dark:text-white">,</span> Jason
          <span className="text-black dark:text-white">,</span> Lucas
          <span className="text-black dark:text-white">,</span> Thomas
          <span className="text-black dark:text-white">,</span> Marmotte
        </p>
      </Link>
    </footer>
  );
};
