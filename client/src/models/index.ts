export type TableActivity = {
  id: string;
  title: string;
  price: string;
  rating: number;
  hasSpecialOffer: string;
  supplierInfo: string;
};

export type Activity = {
  id: string;
  title: string;
  price: number;
  rating: number;
  currency: string;
  specialOffer: boolean;
  supplier: Supplier;
};

export type Supplier = {
  address: string;
  city: string;
  country: string;
  name: string;
  zip: string;
};
