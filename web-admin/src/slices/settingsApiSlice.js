import {
    SETTINGS_URL,
    CURRENCIES_URL,
    COUNTRIES_URL
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
        getCurrencies: builder.query({
            query: () => ({
                url: `${CURRENCIES_URL}`,
                method: 'GET'
            }),
            headers: {
                "Content-Type": "application/json"
            },
            keepUnusedDataFor: 5,
        }),
        getCountries: builder.mutation({
            query: () => ({
                url: `${COUNTRIES_URL}`,
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
    useGetCurrenciesQuery,
    useGetCountriesMutation
} = settingsApiSlice