import { css } from "@emotion/react";
import { useMemo } from "react";

const MainContent = () => {
    const styles = useMemo(() => css``, [])
    return <>
        <div css={styles}>
            MainContent
        </div>
    </>
}
export default MainContent;