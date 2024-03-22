
export const updateCart = (state) => {
    // items price
    state.itemsPrice = addDecimals(
        state.cartItems
            .reduce((acc, item) => acc + item.price * item.quantity, 0))

    //
    state.shippingPrice = addDecimals(state.itemsPrice > 100 ? 0 : 10)
    state.taxPrice = addDecimals(
        Number(0.15 * state.itemsPrice)
            .toFixed(2)
    )

    state.totalPrice = (
        Number(state.itemsPrice) +
        Number(state.shippingPrice) +
        Number(state.taxPrice)
    ).toFixed(2)

    localStorage.setItem('cart', JSON.stringify(state))
}

export const addDecimals = (num) => {
    return (Math.round(num * 100) / 100).toFixed(2)
}