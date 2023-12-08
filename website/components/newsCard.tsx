"use client";
import React from "react";
import { Button } from "@nextui-org/react";
import { Image } from "@nextui-org/react";

interface NewsCardProps {
  imageUrl: string;
  title: string;
  description: string;
  articleLink: string;
}

const NewsCard: React.FC<NewsCardProps> = ({
  imageUrl,
  title,
  description,
  articleLink,
}) => {
  const truncatedDescription =
    description.length > 150 ? `${description.slice(0, 150)}...` : description;

  return (
    <div className="border border-gray-300 rounded-2xl overflow-hidden w-96">
      <Image src={imageUrl} alt={title} className="w-96 h-60 object-cover" />
      <div className="p-4">
        <h2 className="text-xl font-bold mb-2">{title}</h2>
        <p className="mb-4 line-clamp-3">{truncatedDescription}</p>
      </div>
      <div className="flex justify-end p-4">
        <Button size="sm" className="bg-primary-300">
          <a href={articleLink} className="text-white">
            Learn More
          </a>
        </Button>
      </div>
    </div>
  );
};

export default NewsCard;
