import { useMemo } from 'react';
import type { CSSProperties, FC } from 'react';
import type { FlexProps } from './FlexLayout_Types';
import React from 'react';
import { useClassNameStr, useFlexSpacings, useItemStyle } from './FlexLayout_CustomHooks';
import { css } from '@emotion/react';

const Flex: FC<FlexProps> = ({
    children,
    className,
    spacing = 'medium',
    itemStyle,
    vertical,
    wrap,
    justifyContent = 'center',
    alignItems = 'center',
}) => {
    const flexSpacing = useFlexSpacings(spacing);
    const childStyle = useItemStyle(itemStyle);

    const wrapperClass: string = useMemo(() => {
        return useClassNameStr([className, `flex`, vertical ? `flex-col` : undefined, wrap ? `flex-wrap` : undefined]);
    }, [vertical, wrap]);

    const wrapperCss = useMemo(
        () => css`
            justify-content: ${justifyContent};
            align-items: ${alignItems};
        `,
        []
    );

    const wrapperStyle: CSSProperties = useMemo(() => {
        const [flexSpacingX, flexSpacingY] = flexSpacing;
        return {
            gap: `${flexSpacingX}px ${flexSpacingY}px`,
        };
    }, [flexSpacing]);

    const childClass: string = useMemo(() => {
        return useClassNameStr([`flexLayout_Child`, `flex-[0_0_auto]`]);
    }, []);

    return (
        <>
            <div className={wrapperClass} style={wrapperStyle} css={wrapperCss}>
                {React.Children.toArray(children).map((child, index, oriChilds) => {
                    return (
                        <div key={index} className={childClass} style={childStyle}>
                            {child}
                        </div>
                    );
                })}
            </div>
        </>
    );
};

export default Flex;
