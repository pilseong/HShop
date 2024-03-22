import {
    CUSTOMER_URL,
    AUTH_URL,
    ROLES_URL
} from "../constants";
import { apiSlice } from "./apiSlice";

const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getCustomers: builder.mutation({
            query: (data) => ({
                url: `${CUSTOMER_URL}`,
                method: 'GET',
                params: { ...data },
            }),
        }),
        editCustomer: builder.mutation({
            query: (userId, body) => ({
                url: `${CUSTOMER_URL}/${userId}`,
                method: 'PUT',
                body: body,
                formData: true,
                headers: {
                    "Content-Type": "multipart/form-data"
                }
                // prepareHeaders: (headers) => {

                //     headers.set("Content-Type", "multipart/form-data")
                //     console.log("header", headers)
                //     return headers
                // },
            }),
        }),
        getUserDetails: builder.mutation({
            query: (userId) => ({
                url: `${USERS_URL}/${userId}`
            }),
        }),
    })
})

export const {
    useEditCustomerMutation,
    useGetCustomersMutation,
    useGetUserDetailsMutation,
} = usersApiSlice