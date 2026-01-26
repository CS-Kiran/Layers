import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Layers.ai",
  description: "AI solution to road problems",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
