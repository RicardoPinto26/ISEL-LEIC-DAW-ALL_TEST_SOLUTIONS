import React, {useState} from 'react';
import {createRoot} from "react-dom/client";

function useToggle(initial: boolean): [observed: boolean, toggle: () => void] {
    const [value, setValue] = useState(initial)

    const t = () => {
        setValue(val => !val)
    }

    return [value, t]
}

export function ToggleComponent() {
    const [enabled, toggle] = useToggle(false)

    return (
        <div>
            <p>Toggle Value: {enabled ? "Enabled" : "Disabled"}</p>
            <button onClick={toggle}>Toggle</button>
        </div>
    )
}

export function demo() {
    const root = createRoot(document.getElementById("container"))
    root.render(<ToggleComponent/>)
}