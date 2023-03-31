/* {name,prefix,color,...props}:{name:string,prefix:string,color:string} & React.SVGProps<SVGSVGElement>  */
const SVGIcon = ({ name, prefix = `icon`, color, ...props }) => {
    const symbolId = `#${prefix}-${name}`;
    return (
        <svg {...props} aria-hidden='true'>
            <use href={symbolId} fill={color} />
        </svg>
    );
};

export default SVGIcon;
