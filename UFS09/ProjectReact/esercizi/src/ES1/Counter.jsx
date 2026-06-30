import React from "react";

export const Counter = () => {
    const [count, setCount] = React.useState(0)

    const incrementa = () => {
        setCount(v => v + 1)
    }
    
    const decrementa = () => {
        setCount(v => v - 1)
    }

    const reset = () => {
        setCount(0)
    }

    return (
        <>
            <p>Conta: {count}</p>
            <button onClick={incrementa}>Incrementa</button>
            <button onClick={decrementa} disabled={count === 0}>Decrementa</button>
            <button onClick={reset}>Reset</button>
        </>
    )
}