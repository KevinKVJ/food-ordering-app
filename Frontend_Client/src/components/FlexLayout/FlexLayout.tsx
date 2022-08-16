import { useMemo } from 'react';
import type { CSSProperties, FC } from 'react';
import type { FlexProps } from './FlexLayout_Types';
import React from 'react';
import { useFlexSpacings, useItemStyle } from './FlexLayout_CustomHooks';
import { css } from '@emotion/react';

const Flex: FC<FlexProps> = ({
    children,
    className,
    spacing = 'medium',
    itemStyle,
    vertical,
    wrap,
    justifyContent = 'flex-start',
    alignItems = 'center',
}) => {
    const flexSpacing = useFlexSpacings(spacing);
    const childStyle = useItemStyle(itemStyle);

    const wrapper_css = useMemo(() => {
        const [flexSpacingX, flexSpacingY] = flexSpacing;
        return css`
            /* width:fit-content; */
            display: flex;
            flex-direction: ${vertical ? 'column' : 'row'};
            flex-wrap: ${wrap ? 'wrap' : 'nowrap'};
            justify-content: ${justifyContent};
            align-items: ${alignItems};
            gap: ${flexSpacingX}px ${flexSpacingY}px;
            flex: 0 0 auto;
        `;
    }, [vertical, wrap, justifyContent, alignItems, flexSpacing]);

    const child_css = useMemo(
        () => css`
            flex: 0 0 auto;
        `,
        []
    );

    return (
        <>
            <div className={className} css={wrapper_css}>
                {React.Children.toArray(children).map((child, index, oriChilds) => {
                    return (
                        <div key={index} css={child_css} className='flexLayout_Child' style={childStyle}>
                            {child}
                        </div>
                    );
                })}
            </div>
        </>
    );
};

export default Flex;
