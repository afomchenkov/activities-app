import { TableActivity, Activity, Supplier } from "../models";

export const NO_OP_FN = () => void 0;

export const mapSupplierInfo = (supplier: Supplier): string => {
  if (!supplier) {
    return "--";
  }
  const { name, address, city, country, zip } = supplier;
  return `${name} - ${city}, ${country}, ${address}, ${zip}`;
};

export const mapToColumnItems = (items: Activity[] = []): TableActivity[] => {
  return items.map((item) => {
    const { id, title, price, currency, rating, specialOffer, supplier } = item;
    return {
      id,
      title,
      price: `${currency}${price}`,
      rating,
      hasSpecialOffer: specialOffer ? "+" : "-",
      supplierInfo: mapSupplierInfo(supplier),
    };
  });
};
