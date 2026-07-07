import React, { useEffect } from "react";

export const ProductList = () => {
    const [products, setProducts] = React.useState([])
    const [loading, setLoading] = React.useState(false)

    useEffect(() => {
        (async () => {
            setLoading(true)
            const response = await fetch("https://dummyjson.com/products")
            const json = await response.json()
            setProducts(json.products)
            setLoading(false)
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