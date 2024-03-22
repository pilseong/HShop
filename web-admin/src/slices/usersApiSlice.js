import {
    USERS_URL,
    AUTH_URL,
    ROLES_URL
} from "../constants";
import { apiSlice } from "./apiSlice";

const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (data) => ({
                url: `${AUTH_URL}/login`,
                method: 'POST',
                body: data,
            }),
        }),
        logout: builder.mutation({
            query: () => ({
                url: `${AUTH_URL}/logout`,
                method: 'POST',
            }),
        }),
        getUsers: builder.mutation({
            query: (data) => ({
                url: `${USERS_URL}`,
                method: 'GET',
                params: { ...data },
            }),
        }),
        editUser: builder.mutation({
            query: (userId, body) => ({
                url: `${USERS_URL}/${userId}`,
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
        getAllRoles: builder.query({
            query: () => ({
                url: `${ROLES_URL}`
            }),
            keepUnusedDataFor: 5,
            providesTags: ['Roles']
        }),
    })
})

export const {
    useLoginMutation,
    useLogoutMutation,
    useEditUserMutation,
    useGetUsersMutation,
    useGetUserDetailsMutation,
    useGetAllRolesQuery
} = usersApiSlice