export class User {
    
    // Not sure if correct way to approach, but the only one that worked for me :/
    constructor(
        public id: number = 0,
        public status: string = '',
        public login: string = '',
        public password: string = '',
        public address: string = ''
    ) {}

}
