import { createSlice } from "@reduxjs/toolkit";
import { updateCart } from "../util/cartUtils"

const initialState = localStorage.getItem("cart") ?
    JSON.parse(localStorage.getItem("cart")) :
    { cartItems: [] }


const cartSlice = createSlice({
    name: "Cart",
    initialState,
    reducers: {
        addToCart: (state, action) => {
            const item = action.payload
            const existItem = state.cartItems.find(x => x.id === item.id)
            if (existItem) {
                // 덮어 씌우기
                state.cartItems = state.cartItems
                    .map(x => x.id === existItem.id ? item : x)
            } else {
                state.cartItems = [...state.cartItems, item]
            }
            updateCart(state)
        },
        removeFromCart: (state, action) => {
            state.cartItems = state.cartItems.filter(x => x.id !== action.payload)
            return updateCart(state)
        }
    }
})

export const { addToCart, removeFromCart } = cartSlice.actions
export default cartSlice.reducer