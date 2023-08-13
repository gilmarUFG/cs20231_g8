import MediaFile from "../mediafile.model";

export default interface UserProfile {

    id?: string;
    name: string;
    login: string;
    password: string;
    profilePictureId?: string | null;
    profilePicture?: any;

}