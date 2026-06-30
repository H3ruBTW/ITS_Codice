import React, { useEffect } from "react";

export const ProductList = () => {
    const [products, setProducts] = React.useState([])
    const [loading, setLoading] = React.useState(false)

    const toggleLoading = () => {
        setLoading(v => !v)
    }

    useEffect(() => {
        (async () => {
            toggleLoading()
            const response = await fetch("https://dummyjson.com/products")
            const json = await response.json()
            setProducts(json.products)
            toggleLoading()
        })()
    }, [])

    return (
        <>
            <ul aria-busy={loading}>
                {products.map(p => (
                    <li key={p.id}>{p.title} - {p.price}$</li>
                ))}
            </ul>
        </>
    )
}