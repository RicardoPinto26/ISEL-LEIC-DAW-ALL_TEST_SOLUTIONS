import React, {ComponentType, useEffect, useState} from "react";
import {createRoot} from "react-dom/client";


type ScrollerProps = {
    messages: string[],
    period: number,
    component: ComponentType<{ text: string }>
}

function ScrollerComponent({messages, period, component}: ScrollerProps) {
    const MyComponent = component;

    const [index, setIndex] = useState(0);

    function next() {
        setIndex((i: number) => (i + 1) % messages.length);
    }

    useEffect(() => {
        const intervalID = setInterval(next, period)
        return () => clearInterval(intervalID);

    }, [messages, period, component]);

    return (
        <MyComponent text={messages[index]}/>
    )
}

function HelpComponent(props: { text: string }) {
    return <h1>{props.text}</h1>
}

export function demo() {
    const root = createRoot(document.getElementById("container"))
    root.render(<ScrollerComponent messages={["1", "2", "3"]} period={200} component={HelpComponent} />)
}