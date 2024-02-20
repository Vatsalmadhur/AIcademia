import { ApiProperty } from '@nestjs/swagger';

export class ApiResponseModel {
	constructor({
		status,
		msg,
		errors
	}: {
		status: boolean;
		msg: string;
		errors?: [string];
	}) {
		this.status = status;
		this.msg = msg;
		this.errors = errors;
	}
	@ApiProperty({
		type: 'boolean',
		name: 'status',
		description: 'Request status'
	})
	status: boolean;
	@ApiProperty({
		type: 'string',
		name: 'message',
		description: 'Response message, if any'
	})
	msg: string;
	@ApiProperty({
		type: 'string',
		name: 'errors',
		isArray: true,
		description: 'errors, if any. Will contain errors, if status===false'
	})
	errors?: [string];
}
