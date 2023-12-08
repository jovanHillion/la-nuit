type gradecontainerProps = {
    width: number;
};

export const GradeContainer: React.FC<gradecontainerProps> = ({ width }) => {
    return (
        <div className={`flex flex-row text-[46px] font-bold text-primary-900 bg-primary-600 rounded-full px-[${width}px]`}>
            <div>Grade</div>
            <div className="flex flex-row space-x-12">
                <div className="px-[20px] bg-primary-50 rounded-full">P</div>
                <div>Jovan Hillion</div>
            </div>
            <div>1000</div>
		</div>
    );
}