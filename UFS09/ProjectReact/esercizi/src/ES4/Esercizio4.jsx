import React, { createContext, useContext } from "react"
import { Main } from "./Main"
import { ChangeTheme } from "./ChangeTheme"

export const ThemeContext = createContext()

export const Esercizio4 = () => {

    const [currentTheme, setTheme] = React.useState("light")

    const change = () => {
        setTheme(v => (v === "light") ? "dark" : "light")
    }

    return (
        <ThemeContext.Provider value={{currentTheme, change}}>
            <Main />
            <ChangeTheme />
        </ThemeContext.Provider>
    )
}