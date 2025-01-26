import { useState, ChangeEvent } from "react";

type OnChange = (e: ChangeEvent<HTMLInputElement>) => void;

type UseInputValue = (initialValue: string) => {
  value: string;
  onChange: OnChange;
};

export const useInputValue: UseInputValue = (initialValue) => {
  const [value, setValue] = useState(initialValue);
  const onChange: OnChange = (e) => setValue(e.target.value);
  return { value, onChange };
};
