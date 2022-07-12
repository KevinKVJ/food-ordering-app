import { useMemo } from 'react';
import type { CSSProperties, FC } from 'react';
import type { FlexProps } from './FlexLayout_Types';
import React from 'react';
import { useClassNameStr, useFlexSpacings, useItemStyle } from './customHooks';

const Flex: FC<FlexProps> = ({ children, spacing = 'medium', itemStyle, vertical, wrap }) => {
    const flexSpacing = useFlexSpacings(spacing);
    const childStyle = useItemStyle(itemStyle);

    const wrapperClass: string = useMemo(() => {
        return useClassNameStr([`flex`, vertical ? `flex-col` : undefined, wrap ? `flex-wrap` : undefined]);
    }, [vertical, wrap]);

    const wrapperStyle: CSSProperties = useMemo(() => {
        const [flexSpacingX, flexSpacingY] = flexSpacing;
        return {
            gap: `${flexSpacingX}px ${flexSpacingY}px`,
        };
    }, [flexSpacing]);

    const childClass: string = useMemo(() => {
        return useClassNameStr([`flex-[0_0_auto]`]);
    }, []);

    return (
        <>
            <div className={wrapperClass} style={wrapperStyle}>
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
