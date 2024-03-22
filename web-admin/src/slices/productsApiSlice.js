import { PRODUCTS_URL } from "../constants";
import { apiSlice } from "./apiSlice";

export const productsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        createProduct: builder.mutation({
            query: (body) => ({
                url: `${PRODUCTS_URL}`,
                method: 'POST',
                body
            })
        }),
        getProducts: builder.query({
            query: () => ({
                url: `${PRODUCTS_URL}`,
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Products'],
        }),
        searchProducts: builder.mutation({
            query: (params) => ({
                url: `${PRODUCTS_URL}`,
                method: 'GET',
                params: { ...params }
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Products'],
        }),
        getProduct: builder.query({
            query: (productId) => ({
                url: `${PRODUCTS_URL}/${productId}`,
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Products'],
        })
    }),
})


export const {
    useSearchProductsMutation,
    useCreateProductMutation,
    useGetProductsQuery,
    useGetProductQuery
} = productsApiSlice