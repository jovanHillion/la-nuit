// Use the correct import statement for the Lottie component
import Lottie from 'react-lottie';
import React, { useState, useEffect } from 'react';
import {Spinner} from "@nextui-org/react";

export const HomeLottieFile = () => {
  const [animationData, setAnimationData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("./tree.json");
        if (!response.ok) {
            console.error("Failed to fetch:", response.status, response.statusText);
          throw new Error('Failed to fetch animation data');
        }
        console.log("response", response);
        const jsonData = await response.json();
        setAnimationData(jsonData);
      } catch (error) {
        console.error("Error fetching animation data:", error);
      }
    };
    fetchData();
  }, []);

  return (
    <div className="flex justify-center items-center">
      {animationData ? (
        <Lottie
          options={{
            animationData,
            loop: true, // Set to true if you want the animation to loop
            autoplay: true, // Set to true if you want the animation to play automatically
          }}
          height={300}
          width={300}
        />
      ) : (
        <Spinner color="success"/>
      )}
    </div>
  );
};