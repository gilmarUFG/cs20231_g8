import { styled } from "styled-components";
import Navigation from "./Navigation";
import { Divider } from "../atoms";

type FooterProps = {}

const StyledFooter = styled.footer`
    max-width: 1280px;
    margin: 0 auto;
    padding: 24px 100px;
    display: flex;
    flex-direction: column;
    gap: 32px;
    section {
        display: flex;
        gap: 96px;
        div {
            flex: 0.3;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
    }
    h6 {
        flex: 1;
        text-align: center;
    }
`;

const Footer: React.FunctionComponent<FooterProps> = (props) => {
    return (
        <StyledFooter>
            <section>
                <div>
                    <h3>
                        Pixel Port
                    </h3>
                    <h5>
                        Explore e Conquiste: <br/>A Plataforma Definitiva para Publicação de Imagens!
                    </h5>
                </div>
                <Navigation flexColumn />
            </section>
            <Divider />
            <h6>
                2077 Pixel Port. Todos direitos reservados.
            </h6>
        </StyledFooter>
    );
}

export default Footer;