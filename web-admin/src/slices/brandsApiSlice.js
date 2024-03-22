import {
    BRANDS_URL,
} from "../constants";
import { apiSlice } from "./apiSlice";

const brandsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getBrands: builder.mutation({
            query: (params) => ({
                url: `${BRANDS_URL}`,
                method: 'GET',
                params: { ...params }
            }),
            headers: {
                "Content-Type": "application/json"
            },
            keepUnusedDataFor: 5,
        }),
    })
})

export const {
    useGetBrandsMutation,
} = brandsApiSlice