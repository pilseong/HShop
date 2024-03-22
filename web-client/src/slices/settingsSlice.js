import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    settings: localStorage.getItem('settings') ?
        JSON.parse(localStorage.getItem('settings')) : null
}

const settingsSlice = createSlice({
    name: 'Settings',
    initialState,
    reducers: {
        setSettings: (state, action) => {
            console.log("action", action)
            state.settings = action.payload
            localStorage.setItem('global', JSON.stringify(action.payload))
        },
    }
})

export const { setSettings } = settingsSlice.actions
export default settingsSlice.reducer