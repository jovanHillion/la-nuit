"use client";
import { Logo, LogoWithoutText } from "@/components/icons";
import { HomeLottieFile } from "@/components/homeLottie";
import { GradeContainer } from "@/components/grade";

export default function Home() {
  return (
    
    <div>
      <div className="flex justify-between h-full pt-[15%]">
        <div className="">
          <div className="flex flex-row w-[100%] space-x-[650px]">
            <div className="font-bold text-[48px] text-primary-800 max-w-[690px]">
              <span className="text-primary-200">S</span>pring
              <span className="text-primary-200">Y</span>our
              <span className="text-primary-200">L</span>ife, the fast way to
              <span className="text-primary-200"> Reduce </span>your individual
              <span className="text-primary-200"> C</span>arbon
              <span className="text-primary-200"> F</span>ootprint
            </div>
            <div className="flex justify-center items-center">
              <LogoWithoutText width={150} height={150}/>
            </div>
          </div>
          <div className="font-normal text-[28px] text-primary-700 max-w-[690px]">
            What do you think about a little QUIZZ to evaluate your carbon footprint
          </div>
          <HomeLottieFile/>
        </div>
        
      </div>
      <div className="mt-[5%] mb-[5%]">
        <button className="bg-primary-500 hover:bg-primary-700 w-[139px] h-[62px] rounded-[25px] text-[28px] font-bold">Quizz</button>
      </div>
	  <div className="bg-primary-600 h-full w-full">
			<div className="flex justify-center text-primary-900 font-bold text-[38px]">
				News
			</div>
	  </div>

	  <div className="flex items-center flex-col py-[10%] space-y-12">
		<div className="text-primary-50 font-bold text-[38px] py-[15px] px-[120px] bg-primary-500 rounded-full">
			Scoreboard
		</div>
	  </div>
    </div>
    
  );
}
