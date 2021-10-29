<template>
	<b-container>
		<h1 class="mb-4 mt-4">Send an email to "{{ username }}"</h1>
		<form @submit.prevent="send" @reset="onReset" v-if="show">
			<b-form-group
				id="subjectInputGroup"
				label="Subject:"
				label-for="subjectInput"
                valid-feedback="Done!"
                :invalid-feedback="invalidSubject"
                :state="subjectState"
			>
				<b-form-input
					id="subjectInput"
					v-model.trim="subject"
					placeholder="Enter email's subject"
                    :state="subjectState"
					required
				></b-form-input>
			</b-form-group>
			<b-form-group
				id="messageInputGroup"
				label="Message:"
				label-for="messageInput"
                valid-feedback="Done!"
                :invalid-feedback="invalidMessage"
                :state="messageState"
			>
				<b-form-textarea
					id="messageInput"
					v-model.trim="message"
					placeholder="Enter email's message"
                    :state="messageState"
					rows="5"
					required
				></b-form-textarea>
			</b-form-group>
			<b-button type="submit" :disabled="isFormValid" variant="success">Send</b-button>
			<b-button type="reset">Reset</b-button>
            <nuxt-link to="/students"><b-button variant="warning">Return</b-button></nuxt-link>
		</form>
	</b-container>
</template>

<script>
export default {
	data() {
		return {
			username: null,
			subject: null,
			message: null,
            show: true
		};
	},
	created() {
		this.username = this.$route.params.username;
	},
	computed: {
		subjectState () {
            if(!this.subject) {
                return null;
            }
			return this.subject.length <= 50 && this.subject.length >= 4
		},
        invalidSubject () {
            if(this.subject && (this.subject.length > 50 || this.subject.length < 4)){
                return "Length must be between 4 - 50."
            }
        },
		messageState() {
			if(!this.message) {
                return null;
            }
			return this.message.length <= 500 && this.message.length >= 20
		},
        invalidMessage () {
            if(this.message && (this.message.length > 500 || this.message.length < 20)){
                return "Length must be between 20 - 500."
            }
        },
		isFormValid() {
			if (this.messageState && this.subjectState) {
				return false;
			}
			return true;
		},
	},
	methods: {
		send() {
			this.$axios.post("/api/students/" + this.username + "/email/send", {subject: this.subject, message: this.message})
            .then(msg => {
                this.$toast.success(msg.data).goAway(1500)
            })
            .catch(error => {
                this.$toast.error('Error sending the e-mail!').goAway(3000)
            })
		},
        onReset (event) {
            this.subject = null
            this.message = null
        }
	},
};
</script>