import {
    SETTINGS_URL
} from "../constants";
import { apiSlice } from "./apiSlice";

const settingsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getSettings: builder.mutation({
            query: () => ({
                url: `${SETTINGS_URL}`,
                method: 'GET'
            }),
            headers: {
                "Content-Type": "application/json"
            },
            keepUnusedDataFor: 5,
        }),
    })
})

export const {
    useGetSettingsMutation,
} = settingsApiSlice