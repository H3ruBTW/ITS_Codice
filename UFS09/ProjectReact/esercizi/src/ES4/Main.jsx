import React, { createContext, useContext } from "react"
import { ThemeContext } from "./Esercizio4"

export const Main = () => {
    const {currentTheme} = useContext(ThemeContext)

    return (
        <>
            <h2>Il tema corrente è: {currentTheme}</h2>
        </>
    )
}