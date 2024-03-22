import { CUSTOMER_URL, USERS_URL } from "../constants";
import { apiSlice } from "./apiSlice";

export const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (data) => ({
                url: `${CUSTOMER_URL}/login`,
                method: 'POST',
                body: data
            }),
        }),
        register: builder.mutation({
            query: (data) => ({
                url: `${CUSTOMER_URL}`,
                method: 'POST',
                body: data
            }),
        }),
        logout: builder.mutation({
            query: () => ({
                url: `${CUSTOMER_URL}/logout`,
                method: 'POST',
            }),
        }),
        getUsers: builder.query({
            query: () => ({
                url: `${USERS_URL}`,
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Users'],
        }),
        getUser: builder.query({
            query: (userId) => ({
                url: `${USERS_URL}/${userId}`,
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Products'],
        })
    }),
})


export const {
    useLoginMutation,
    useLogoutMutation,
    useRegisterMutation,
    useGetUsersQuery,
    useGetUserQuery
} = usersApiSlice