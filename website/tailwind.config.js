import { nextui } from "@nextui-org/theme";

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./components/**/*.{js,ts,jsx,tsx,mdx}",
    "./app/**/*.{js,ts,jsx,tsx,mdx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          50: "#ffffff",
          100: "#d9d9d9",
          200: "#01DDB3",
          300: "#00C1A2",
          400: "#000000",
          500: "#31C48D",
          600: "#F3F6F8",
          700: "#929EA9",
          800: "#20272C",
          900: "#1E1E1E",
        },
      },
    },
  },
  darkMode: "class",
  plugins: [nextui()],
};
