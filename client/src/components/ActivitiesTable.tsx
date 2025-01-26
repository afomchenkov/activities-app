import { FC, memo } from "react";
import { TableActivity } from "../models";
import "./ActivitiesTable.scss";

type TableColumn = {
  key: string;
  translation: string;
  width: string;
};

type ActivitiesTableProps = {
  columns: TableColumn[];
  items: TableActivity[];
};

const TableOverlay: FC<{}> = () => {
  return <div className="activities-table__overlay">No Items</div>;
};

const ActivitiesTableComponent: FC<ActivitiesTableProps> = ({
  columns,
  items = [],
}) => {
  const columnsConfig = Object.values(columns);

  const mapToRow = (data: TableActivity) => {
    return columnsConfig.map((column) => (
      <td key={column.key} className="activities-table__row-cell">
        {data[column.key as never]}
      </td>
    ));
  };

  return (
    <div className="activities-table__container">
      <table>
        <thead>
          <tr>
            {columnsConfig.map((column) => {
              const { key, translation, width = "inherit" } = column;

              return (
                <th
                  key={key}
                  className="activities-table__table-col"
                  scope="col"
                  style={{ width }}
                >
                  {translation}
                </th>
              );
            })}
          </tr>
        </thead>
        <tbody>
          {items.map((item) => (
            <tr key={item.id} className="activities-table__row">
              {mapToRow(item)}
            </tr>
          ))}
        </tbody>
      </table>
      {!items.length && <TableOverlay></TableOverlay>}
    </div>
  );
};

export const ActivitiesTable = memo(ActivitiesTableComponent);
