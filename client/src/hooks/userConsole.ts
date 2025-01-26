import { useCallback } from "react";

export const useConsole = () => {
  const consoleLog = useCallback((data: string) => {
    console.log(data);
  }, []);

  const consoleError = useCallback((data: string) => {
    console.error(data);
  }, []);

  const consoleWarn = useCallback((data: string) => {
    console.warn(data);
  }, []);

  return {
    consoleError,
    consoleLog,
    consoleWarn,
  };
};
