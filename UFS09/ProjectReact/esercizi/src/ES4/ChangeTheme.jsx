import React, { createContext, useContext } from "react"
import { ThemeContext } from "./Esercizio4"

export const ChangeTheme = () => {
    const {change} = useContext(ThemeContext)
    return (
        <>
            <button onClick={change}>Cambia tema</button>
        </>
    )
}