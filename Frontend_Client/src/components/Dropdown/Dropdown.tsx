import { css } from '@emotion/react';
import { useLayoutEffect, useMemo, useState } from 'react';

import { IDropdownProps } from './DropdownType';

const Dropdown = ({ children, activeSwitch }: IDropdownProps) => {
    const [dropdownActive, setDropdownActive] = useState(false);

    useLayoutEffect(() => {
        setDropdownActive(activeSwitch);
    }, [activeSwitch]);

    const dropdownWrapperStyle = useMemo(
        () => css`
            position: relative;
        `,
        []
    );
    const dropdownStyle = useMemo(
        () => css`
            position: absolute;
            top: 30px;

            border-radius: 3px;
            box-shadow: 0 3px 6px -4px rgba(0, 0, 0, 0.24),
                0 6px 12px 0 rgba(0, 0, 0, 0.16),
                0 9px 18px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            height: 300px;
            background-color: red;
        `,
        []
    );
    return dropdownActive ? (
        <div css={dropdownWrapperStyle}>
            <div css={dropdownStyle}>{children}</div>
        </div>
    ) : null;
};

export default Dropdown;
