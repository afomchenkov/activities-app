import { FC, useCallback } from "react";
import { useQuery } from "@apollo/client";
import GET_ACTIVITIES_LIST from "../gql/queries/activities";
import { ActivitiesTable } from "./ActivitiesTable";
import { ActivitiesTableMenu } from "./ActivitiesTableMenu";
import { ActivitiesTablePager } from "./ActivitiesTablePager";
import { ITEMS_LIMIT, TABLE_COLUMNS } from "../constants";
import { mapToColumnItems } from "../utils";
import "./ActivitiesList.scss";

export const ActivitiesList: FC = () => {
  const { loading, error, data, refetch } = useQuery(GET_ACTIVITIES_LIST, {
    variables: {
      limit: ITEMS_LIMIT,
      offset: 0,
      search: "",
    },
  });

  const handleSearchQuery = useCallback((query: string): void => {
    refetch({
      search: query,
      offset: 0,
    });
  }, [refetch]);

  const handlePageClick = useCallback((page: number): void => {
    refetch({
      offset: page,
    });
  }, [refetch]);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>An error occurred...</p>;
  }

  const { activitiesList } = data || {};

  return (
    <div className="activities-list__container">
      <ActivitiesTableMenu onSearch={handleSearchQuery} />
      <ActivitiesTable
        columns={TABLE_COLUMNS}
        items={mapToColumnItems(activitiesList?.activities)}
      />
      <ActivitiesTablePager
        {...activitiesList}
        stepLimit={ITEMS_LIMIT}
        onPageClick={handlePageClick}
      />
    </div>
  );
};
