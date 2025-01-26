import { FC, useEffect, memo } from "react";
import { useInputValue, useDebounce } from "../hooks";
import "./ActivitiesTableMenu.scss";

type ActivitiesTableMenuProps = {
  onSearch: (query: string) => void;
};

const ActivitiesTableMenuComponent: FC<ActivitiesTableMenuProps> = ({
  onSearch,
}) => {
  const { value, onChange } = useInputValue("");
  const lastValue = useDebounce(value, 500);

  useEffect(() => {
    onSearch(lastValue);
  }, [onSearch, lastValue]);

  return (
    <div className="activities-table-menu">
      <div className="activities-table-menu__search">
        <input
          autoFocus
          onChange={onChange}
          placeholder="Search..."
          type="text"
          value={value}
        />
      </div>
    </div>
  );
};

export const ActivitiesTableMenu = memo(ActivitiesTableMenuComponent);
