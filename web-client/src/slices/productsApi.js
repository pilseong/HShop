import { CATEGORY_URL } from "../constants";
import { PRODUCT_URL } from "../constants";
import { apiSlice } from "./apiSlice";

export const productsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getCategories: builder.query({
            query: (params) => ({
                url: `${CATEGORY_URL}`,
                params: { ...params }
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Category'],
        }),
        getCategory: builder.query({
            query: (key) => ({
                url: `${CATEGORY_URL}/${key}`,
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Category'],
        }),
        getProducts: builder.query({
            query: (params) => ({
                url: `${PRODUCT_URL}`,
                params: { ...params }
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Product'],
        }),
        getProductsByCategoryId: builder.mutation({
            query: (params) => ({
                url: `${CATEGORY_URL}/${params.categoryId}/products`,
                method: 'GET',
                params: { ...params.params }
            }),
            keepUnusedDataFor: 5,
        }),
        getProduct: builder.query({
            query: (productId) => ({
                url: `${PRODUCT_URL}/${productId}`,
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Product'],
        })
    }),
})


export const {
    useGetProductsByCategoryIdMutation,
    useGetCategoryQuery,
    useGetCategoriesQuery,
    useGetProductsQuery,
    useGetProductQuery
} = productsApiSlice