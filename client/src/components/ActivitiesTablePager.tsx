import { FC, useMemo, useCallback, memo } from "react";
import "./ActivitiesTablePager.scss";

type ActivitiesTablePagerProps = {
  totalCount: number;
  stepLimit: number;
  offset: number;
  onPageClick: (page: number) => void;
};

const ActivitiesTablePagerComponent: FC<ActivitiesTablePagerProps> = ({
  totalCount,
  stepLimit = 1,
  offset,
  onPageClick,
}) => {
  const numberOfPages =
    totalCount < stepLimit ? 1 : Math.ceil(totalCount / stepLimit);

  const isActivePage = useCallback(
    (idx: number): boolean => {
      return offset / stepLimit + 1 === idx;
    },
    [stepLimit, offset]
  );

  const pagerButtons = useMemo(() => {
    return Array.from(
      { length: numberOfPages },
      (_, idx: number) => idx + 1
    ).map((idx: number) => {
      return (
        <button
          className="activities-table-pager__button"
          key={idx}
          disabled={isActivePage(idx)}
          onClick={() => onPageClick(stepLimit * idx - stepLimit)}
        >
          {idx}
        </button>
      );
    });
  }, [numberOfPages, onPageClick, stepLimit, isActivePage]);

  return (
    <div className="activities-table-pager__container">
      <div className="activities-table-pager__buttons">{pagerButtons}</div>
    </div>
  );
};

export const ActivitiesTablePager = memo(ActivitiesTablePagerComponent);
