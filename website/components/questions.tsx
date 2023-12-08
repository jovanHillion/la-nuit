import { LittleLogo, Logo } from "./icons";

type QuestionsProps = {
  question: string;
};

export const Questions = (props: QuestionsProps) => {
  return (
    <>
      <div className="w-full flex flex-row bg-primary-800 items-center px-6 py-6 rounded-3xl">
        {/* <div className=""></div> */}
        <div className="w-[30%] flex">
          <LittleLogo width={200} height={200} />
        </div>
        <div className="w-[70%] flex">
          <div className="text-5xl font-bold text-white">{props.question}</div>
        </div>
      </div>
    </>
  );
};
