import React from "react";

export const Counter = () => {
    const [count, setCount] = React.useState(0)

    const onClick = () => {
        setCount(v => v + 1)
    }

    React.useEffect(() => {
        document.title = "Hai cliccato " + count + " volte il titolo"
    }, [count])


    return (
        <div >
            <h2 onClick={onClick} style={{cursor: "pointer"}}>Clicca qui</h2>
        </div>
    )
}